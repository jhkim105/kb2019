package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "ts_search_log")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString()
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SearchLog implements Serializable {

  @Id
  @Column(name = "id", length = ColumnLength.ID)
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @GeneratedValue(generator = "system-uuid")
  private String id;

  @Column(name = "keword", length = ColumnLength.KEYWORD)
  private String keyword;

  @CreatedDate
  @Column(name = "created_date")
  private Long createdDate;

  @Column(name = "created_by", length = ColumnLength.ID)
  private String createdBy;

}

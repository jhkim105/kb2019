package com.example.demo.searchlog;

import com.example.demo.base.ColumnLength;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Table(name = "tl_search_log")
@Getter
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class SearchLog implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "keyword", length = ColumnLength.KEYWORD)
  private String keyword;

  @CreatedDate
  @Column(name = "created_date")
  private Long createdDate;

  @Column(name = "created_by", length = ColumnLength.ID)
  private String createdBy;

  @Builder
  public SearchLog(String keyword) {
    this.keyword = keyword;
  }

}

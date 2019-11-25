package com.example.demo.searchlog;

import com.example.demo.base.AbstractEntity;
import com.example.demo.common.ColumnLength;
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
import java.util.Date;

@Entity
@Table(name = "tl_search_log")
@Getter
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString()
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class SearchLog extends AbstractEntity<Long> {

  private static final long serialVersionUID = 4576947996013305555L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "keyword", length = ColumnLength.KEYWORD)
  private String keyword;

  @CreatedDate
  @Column(name = "created_date")
  private Date createdDate;

  @Column(name = "created_by")
//  @CreatedBy
  private Long createdBy;

  @Builder
  public SearchLog(String keyword, Long createdBy) {
    this.keyword = keyword;
    this.createdBy = createdBy;
  }

}

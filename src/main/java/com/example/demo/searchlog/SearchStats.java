package com.example.demo.searchlog;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tl_search_stats")
@EqualsAndHashCode(callSuper = false, of = "id")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class SearchStats {
  @Id
  private String id;

  @ColumnDefault("1")
  @Column(name = "search_count", nullable = false)
  private Long searchCount;

  @Column(name = "created_date", updatable = false)
  @CreatedDate
  private Date createdDate;

  @Column(name = "updated_date")
  @LastModifiedDate
  private Date updatedDate;

  @Builder
  public SearchStats(String id) {
    this.id = id;
  }

  public void addCount() {
    this.searchCount++;
  }
}

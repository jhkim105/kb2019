package com.example.demo.searchlog;

import com.example.demo.base.AbstractEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class SearchStats extends AbstractEntity<String> {

  private static final long serialVersionUID = 6602872994569237240L;

  @Id
  private String id;

  @ColumnDefault("0")
  @Column(name = "search_count", nullable = false)
  private long searchCount;

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

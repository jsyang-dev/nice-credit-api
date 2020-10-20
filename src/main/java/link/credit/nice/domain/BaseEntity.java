package link.credit.nice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "NO")
  private Long no;

  @CreatedDate
  @Column(name = "CREATE_DT", nullable = false, updatable = false)
  private LocalDateTime createDate;

  // todo(sapzape) 임시 주석 처리
  //  @Column(name = "CREATE_USER", length = 6, nullable = false, updatable = false)
  @Column(name = "CREATE_USER", length = 6, updatable = false)
  private String createUser;

  @LastModifiedDate
  @Column(name = "UPDATE_DT")
  private LocalDateTime updateDate;

  @Column(name = "UPDATE_USER", length = 6)
  private String updateUser;

  @Column(name = "DEL_YN", nullable = false)
  @Builder.Default
  private boolean delYn = false;
}

package org.ediary.db.diary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "diary")
public class DiaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(length = 10, nullable = false)
    private String emotionId;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column()
    private LocalDateTime deletedAt;


}
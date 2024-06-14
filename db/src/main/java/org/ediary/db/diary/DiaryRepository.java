package org.ediary.db.diary;

import org.ediary.db.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity,Long> {

    List<DiaryEntity> findAllByMemberId(Long memberId);
}

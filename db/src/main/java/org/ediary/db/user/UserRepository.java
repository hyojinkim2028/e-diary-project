package org.ediary.db.user;

import org.ediary.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // select * from user where id = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByEmailAndStatusOrderByUserIdDesc(String email, UserStatus userStatus);

    // select * from user where email = ? and password =? and status = > order by id desc limit 1
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByUserIdDesc(String email, String password, UserStatus userStatus);
}

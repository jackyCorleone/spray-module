package com.hxyw.shareadv.repository;

import com.hxyw.shareadv.entity.User;

/**
 * info:
 * Created by shang on 16/7/26.
 */
public interface UserRepository extends BaseRepository<User> {

    User findByUsername(String username);;
}

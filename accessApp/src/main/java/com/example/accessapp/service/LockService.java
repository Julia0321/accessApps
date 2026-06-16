package com.example.accessapp.service;

import com.example.accessapp.model.Lock;

public interface LockService {

    Lock toggleLock(Long lockId, Lock lock);

    Lock addLock(Lock lock);
}

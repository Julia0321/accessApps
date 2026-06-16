package com.example.accessapp.controller;

import com.example.accessapp.model.Lock;
import com.example.accessapp.service.LockServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lock")
public class LockController {

    private final LockServiceImpl lockServiceImpl;

    public LockController(LockServiceImpl lockServiceImpl) {
        this.lockServiceImpl = lockServiceImpl;
    }

    @PostMapping
    public Lock addLock(@RequestBody Lock lock) {
        return lockServiceImpl.addLock(lock);
    }

    @PutMapping("/{lockId}")
    public Lock toggleLock(@PathVariable Long lockId, @RequestBody Lock lock) {
        return lockServiceImpl.toggleLock(lockId, lock);
    }


}

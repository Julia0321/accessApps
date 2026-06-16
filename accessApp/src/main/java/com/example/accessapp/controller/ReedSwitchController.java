package com.example.accessapp.controller;

import com.example.accessapp.model.ReedSwitch;
import com.example.accessapp.service.ReedSwitchServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reedSwitch")
public class ReedSwitchController {

    private final ReedSwitchServiceImpl reedSwitchServiceImpl;

    ReedSwitchController(ReedSwitchServiceImpl reedSwitchServiceImpl) {
        this.reedSwitchServiceImpl = reedSwitchServiceImpl;
    }

    @PostMapping
    public ReedSwitch addReedSwitch(@RequestBody ReedSwitch reedSwitch) {
        return reedSwitchServiceImpl.addReedSwitch(reedSwitch);
    }

    @GetMapping
    public List<ReedSwitch> getReedSwitches() {
        return reedSwitchServiceImpl.getReedSwitchData();
    }

    @PutMapping("/{reedSwitchId}")
    public ReedSwitch updateReedSwitchData(@PathVariable Long reedSwitchId, @RequestBody ReedSwitch reedSwitch) {
        return reedSwitchServiceImpl.updateReedSwitchData(reedSwitchId, reedSwitch);
    }
}

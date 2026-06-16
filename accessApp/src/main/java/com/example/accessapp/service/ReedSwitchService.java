package com.example.accessapp.service;

import com.example.accessapp.model.ReedSwitch;

import java.util.List;

public interface ReedSwitchService {

    ReedSwitch addReedSwitch(ReedSwitch reedSwitch);

    List<ReedSwitch> getReedSwitchData();

    ReedSwitch updateReedSwitchData(Long reedSwitchId, ReedSwitch reedSwitch);
}

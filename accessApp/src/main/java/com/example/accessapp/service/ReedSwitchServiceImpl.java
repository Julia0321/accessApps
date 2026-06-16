package com.example.accessapp.service;

import com.example.accessapp.exception.ReedSwitchError;
import com.example.accessapp.exception.ReedSwitchException;
import com.example.accessapp.model.ReedSwitch;
import com.example.accessapp.repository.ReedSwitchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReedSwitchServiceImpl implements ReedSwitchService {

    private final ReedSwitchRepository reedSwitchRepository;

    ReedSwitchServiceImpl(ReedSwitchRepository reedSwitchRepository) {
        this.reedSwitchRepository = reedSwitchRepository;
    }

    public ReedSwitch addReedSwitch(ReedSwitch reedSwitch) {
        return reedSwitchRepository.save(reedSwitch);
    }

    public List<ReedSwitch> getReedSwitchData() {
        return reedSwitchRepository.findAll();
    }

    public ReedSwitch updateReedSwitchData(Long reedSwitchId, ReedSwitch reedSwitch) {
        return reedSwitchRepository.findById(reedSwitchId)
                .map(reedSwitchFromDb -> {
                    reedSwitchFromDb.setDoorState(reedSwitch.getDoorState());
                    return reedSwitchRepository.save(reedSwitchFromDb);
                }).orElseThrow(() -> new ReedSwitchException(ReedSwitchError.REED_SWITCH_NOT_FOUND));
    }
}

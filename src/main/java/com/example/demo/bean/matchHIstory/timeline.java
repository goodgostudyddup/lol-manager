package com.example.demo.bean.matchHIstory;

import lombok.Data;

@Data
public class timeline {
    //creepsPerMinDeltas存储每分钟击杀的小兵数
    private String creepsPerMinDeltas;
    //csDiffPerMinDeltas 存储每分钟击杀的小兵数差
    private String csDiffPerMinDeltas;
    private String damageTakenDiffPerMinDeltas;
    private String damageTakenPerMinDeltas;
    private String goldPerMinDeltas;
    private String lane;
    private Integer participantId;
    private String role;
    private String xpDiffPerMinDeltas;
    private String xpPerMinDeltas;
}

package com.SmartContactManager.Scm10.scm.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Message {
    private String content;
    @Builder.Default
    private MessageType type=MessageType.blue;

    
}

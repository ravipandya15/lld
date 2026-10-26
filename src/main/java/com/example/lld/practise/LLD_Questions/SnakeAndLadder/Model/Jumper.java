package com.example.lld.practise.LLD_Questions.SnakeAndLadder.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// @Data = @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
public abstract class Jumper {
    private int startPosition;
    private int endPosition;
}

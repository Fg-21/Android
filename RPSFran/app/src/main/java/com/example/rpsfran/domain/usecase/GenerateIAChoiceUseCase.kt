package com.example.rpsfran.domain.usecase

import com.example.rpsfran.domain.model.GameChoice
import kotlin.random.Random

class GenerateIAChoiceUseCase {
    operator fun invoke(): GameChoice {
        val choices = listOf(GameChoice.PIEDRA, GameChoice.PAPEL, GameChoice.TIJERAS)
        return choices[Random.nextInt(choices.size)]
    }
}
package com.hddev.portfoliobackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackages = ["com.hddev.portfoliobackend.entities"])
class PortfolioBackendApplication

fun main(args: Array<String>) {
	runApplication<PortfolioBackendApplication>(*args)
}


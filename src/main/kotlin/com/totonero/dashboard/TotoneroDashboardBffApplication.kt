package com.totonero.dashboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class TotoneroDashboardBffApplication

fun main(args: Array<String>) {
	runApplication<TotoneroDashboardBffApplication>(*args)
}

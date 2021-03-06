package com.example.smartclass

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.URL

class http_req {
    var initUrl: String = "http://192.168.100.79:8080"

        // getter
        get() = field

        // setter
        set(value) {
            field = value
        }

    fun http_get(param : String?){
        val connection = URL(initUrl+param).openConnection()
        BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
            var line: String?
            while (inp.readLine().also { line = it } != null){
                println(line)
            }
        }
    }

    fun http_post(data: String?, end_point: String?){
        var url = URL(initUrl+end_point)
        var postData = "foo1=bar1&foo2=bar2"

        val conn = url.openConnection()
        conn.doOutput = true
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        conn.setRequestProperty("Content-Length", postData.length.toString())

        DataOutputStream(conn.getOutputStream()).use { it.writeBytes(postData) }
        BufferedReader(InputStreamReader(conn.getInputStream())).use { bf ->
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                println(line)
            }
        }
    }



}
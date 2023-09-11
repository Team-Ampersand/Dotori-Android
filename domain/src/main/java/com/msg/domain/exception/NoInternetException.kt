package com.msg.domain.exception

class NoInternetException: RuntimeException() {
    override val message: String
        get() = "인터넷 접속이 원활하지 않습니다. 연결 상태를 확인해 주세요."
}
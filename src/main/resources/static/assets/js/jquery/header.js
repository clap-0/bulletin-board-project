$(document).ready(function () {
    /**
     * 로그아웃 링크에서 click 이벤트 발생시 서버에 로그아웃 요청을 보내는 함수이다.
     */
    $("#logout-link").on("click", (event) => {
        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: '/logout',
            headers: { "content-type": "application/json"},
            dataType: 'text',
        })
        .always(() => window.location.href = "/")
    })
})
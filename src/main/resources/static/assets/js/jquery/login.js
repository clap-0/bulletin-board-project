$(document).ready(function () {
    const loginForm = document.getElementById("loginForm");
    const emailNode = loginForm.querySelector("#email");
    const passwordNode = loginForm.querySelector("#password");


    // 기존 에러메시지들을 전부 삭제하는 함수
    const clearErrorMessages = () => {
        document.querySelectorAll(".errMsg")
                .forEach(errorMessage => errorMessage.remove());
    };

    // 에러메시지를 생성하는 함수
    const createErrorMessage = (message) => {
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = message;
        errorMessage.classList.add("errMsg");
        return errorMessage;
    };

    // 에러메시지를 출력하는 함수
    const showErrorMessages = (response) => {
        clearErrorMessages();

        const responseJSON = JSON.parse(response.responseText);
        const {fieldErrors, message} = responseJSON;

        if (message) {
            const parentNode = document.querySelector(".form-content");
            const errorMessage = createErrorMessage(message);
            $(errorMessage).prependTo(parentNode);
        }
        if (Array.isArray(fieldErrors) && fieldErrors.length) {
            // 새 에러메시지들 추가
            fieldErrors.forEach((fieldError) => {
                const parentNode = document.getElementById(fieldError.field);
                const errorMessage = createErrorMessage(fieldError.message);
                $(errorMessage).appendTo($(parentNode).parent());
            })
        }
    };

    // 로그인 폼에서 제출 시 비동기로 데이터베이스를 확인해 로그인하는 함수
    $("#loginForm").on("submit", (event) => {
        event.preventDefault();
        const email = emailNode.value;
        const password = passwordNode.value;

        $.ajax({
            type: 'POST',
            url: '/login',
            headers: { "content-type": "application/json"},
            dataType: 'text',
            data: JSON.stringify({email, password}),
            success: () => {
                alert("로그인이 완료되었습니다.");
                window.location.href = "/";
            },
            error: (result) => {
                showErrorMessages(result);
            }
        })
    })
})
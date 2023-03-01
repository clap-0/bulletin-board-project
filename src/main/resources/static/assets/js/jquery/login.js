$(document).ready(function () {
    const loginForm = document.getElementById("loginForm");
    const emailNode = loginForm.querySelector("#email");
    const passwordNode = loginForm.querySelector("#password");


    /**
     * 기존 에러메시지들을 전부 삭제하는 함수이다.
     */
    const clearErrorMessages = () => {
        document.querySelectorAll(".errMsg")
                .forEach(errorMessage => errorMessage.remove());
    };

    /**
     * 에러 메시지를 담은 HTML 요소를 생성하는 함수이다.
     *
     * @param message 에러 메시지
     * @returns {HTMLParagraphElement} HTML Paragraph 요소
     */
    const createErrorMessage = (message) => {
        const errorMessage = document.createElement("p");
        errorMessage.innerHTML = message;
        errorMessage.classList.add("errMsg");
        return errorMessage;
    };

    /**
     * 에러 메시지들을 출력하는 함수이다.
     *
     * @param response HTTP로 컨트롤러에게 받은 응답
     */
    const showErrorMessages = (response) => {
        clearErrorMessages();

        const responseJSON = JSON.parse(response.responseText);
        const {fieldErrors, message} = responseJSON;

        /** HTTP 응답으로 받은 에러 메시지를 출력 */
        if (message) {
            const parentNode = document.querySelector(".form-content");
            const errorMessage = createErrorMessage(message);
            $(errorMessage).prependTo(parentNode);
        }

        /** 필드 에러에 대한 새로운 에러 메시지들을 출력 */
        if (Array.isArray(fieldErrors) && fieldErrors.length) {
            fieldErrors.forEach((fieldError) => {
                const parentNode = document.getElementById(fieldError.field);
                const errorMessage = createErrorMessage(fieldError.message);
                $(errorMessage).appendTo($(parentNode).parent());
            })
        }
    };

    /**
     * 로그인 폼에서 submit 이벤트 발생시 비동기로 로그인 요청을 보내는 함수이다.
     */
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
                window.location.replace("/");
            },
            error: (result) => {
                showErrorMessages(result);
            }
        })
    })
})
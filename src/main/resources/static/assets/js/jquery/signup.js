$(document).ready(function () {
    const signupForm = document.getElementById("signupForm");
    const emailNode = signupForm.querySelector("#email");
    const passwordNode = signupForm.querySelector("#password");
    const passwordConfirmationNode = signupForm.querySelector("#passwordConfirmation");
    const userNameNode = signupForm.querySelector("#userName");

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

    // 회원가입 폼에서 제출 시 비동기로 회원을 등록하는 함수
    $("#signupForm").on("submit", (event) => {
        event.preventDefault();
        const email = emailNode.value;
        const password = passwordNode.value;
        const passwordConfirmation = passwordConfirmationNode.value;
        const userName = userNameNode.value;

        $.ajax({
            type: 'POST',
            url: '/users/new',
            headers: { "content-type": "application/json"},
            dataType: 'text',
            data: JSON.stringify({email, password, passwordConfirmation, userName}),
            success: () => {
                clearErrorMessages();
                alert("회원가입이 완료되었습니다. 로그인페이지로 이동합니다.");
                window.location.href = "/login";
            },
            error: (response) => {
                console.log(response);
                showErrorMessages(response);
            }
        })
    })
})
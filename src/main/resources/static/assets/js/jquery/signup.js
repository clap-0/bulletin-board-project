$(document).ready(function () {
    const signupForm = document.getElementById("signupForm");
    const emailNode = signupForm.querySelector("#email");
    const passwordNode = signupForm.querySelector("#password");
    const passwordConfirmationNode = signupForm.querySelector("#passwordConfirmation");
    const userNameNode = signupForm.querySelector("#userName");

    // 기존 에러메시지들을 전부 삭제하는 함수
    let removeErrorMessages = function () {
        const errorMessages = signupForm.querySelectorAll(".errMsg");
        errorMessages.forEach(errorMessage => errorMessage.remove());
    };

    // 에러메시지를 출력하는 함수
    let showErrorMessages = function (result) {
        if (!Object.hasOwn(result, 'responseText')) {
            return;
        }
        const responseJSON = JSON.parse(result.responseText);
        if (!Object.hasOwn(responseJSON, 'errors')) {
            return;
        }
        const errors = responseJSON.errors;

        removeErrorMessages();

        // 새 에러메시지들 추가
        errors.forEach((error) => {
            const parentNode = document.getElementById(error.field);
            const errorMessage = document.createElement("p");
            errorMessage.innerHTML = error['message'];
            errorMessage.classList.add("errMsg");
            $(errorMessage).appendTo($(parentNode).parent());
        })
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
                removeErrorMessages();
                alert("회원가입이 완료되었습니다. 로그인페이지로 이동합니다.");
                window.location.href = "/login";
            },
            error: (result) => {
                console.log(result);
                showErrorMessages(result);
            }
        })
    })
})
$(document).ready(function () {
    /**
     *
     */
    $("#postForm").on("submit", (event) => {
        event.preventDefault();
        console.log(event);

        const params = new URLSearchParams(location.search);
        const pno = params.get('pno');

        let data = {
            boardId: $(".form-content__board-select").val(),
            title: $(".form-content__title").val(),
            content: $(".form-content__content").val(),
        };

        data = JSON.stringify(data);

        if (pno !== null) {
            requestPatch(pno, data);
        } else {
            requestPost(data);
        }
    })


    /**
     * 서버에 게시글 수정 요청을 보내는 메서드이다.
     *
     * @param pno 수정할 게시글의 ID
     * @param data 수정할 게시글 정보를 직렬화한 데이터
     */
    const requestPatch = (pno, data) => {
        $.ajax({
            type: 'PATCH',
            url: '/posts/' + pno,
            headers: { "content-type": "application/json"},
            dataType: 'text',
            data,
            success: () => {
                window.location.href = "/";
            },
            error: (response) => {
                console.log(response);
            }
        })
    }

    /**
     * 서버에 게시글 등록 요청을 보내는 메서드이다.
     *
     * @param data 등록할 게시글 정보를 직렬화한 데이터
     */
    const requestPost = (data) => {
        $.ajax({
            type: 'POST',
            url: '/posts',
            headers: { "content-type": "application/json"},
            dataType: 'text',
            data,
            success: () => {
                window.location.href = "/";
            },
            error: (response) => {
                console.log(response);
            }
        })
    }

    /**
     * 전체 하위 게시판 목록을 서버에서 읽어온 다음 option 요소들을 생성하는 함수이다.
     *
     * @param bno option 요소에 selected 속성을 부여할 게시판의 ID
     */
    const boardSelectHandler = (bno) => {
        $.ajax({
            type: 'GET',
            url: '/boards/sub',
            headers: { "content-type": "application/json"},
            dataType: 'text',
            success: (response) => {
                const boardList = JSON.parse(response);
                renderBoardSelect(boardList, bno);
            },
            error: (response) => {
                console.log(response);
            }
        })
    }

    /**
     * select 요소 아래에 게시판 목록들에 대한 option 요소를 생성하는 함수이다.
     *
     * @param boardList 게시판 목록
     * @param bno selected 속성을 부여할 게시판의 ID
     */
    const renderBoardSelect = (boardList, bno) => {
        const boardSelect = document.querySelector(".form-content__board-select");

        boardList.forEach((board) => {
            const option = document.createElement("option");
            option.value = board.boardId;
            option.textContent = board.boardName;

            if (bno === board.boardId) {
                option.setAttribute("selected", "");
            }

            boardSelect.appendChild(option);
        })
    }

    /**
     * 서버에서 읽어온 게시글의 데이터로 입력란을 채우는 함수이다.
     *
     * @param pno 게시글 ID
     */
    const modifyPageHandler = (pno) => {
        $.ajax({
            type: 'GET',
            url: '/posts/'+pno,
            headers: { "content-type": "application/json"},
            dataType: 'text',
            success: (response) => {
                const postInfo = JSON.parse(response);

                $(".form-content__title").val(postInfo.title);
                $(".form-content__content").val(postInfo.content);

                boardSelectHandler(postInfo.boardId);
            },
            error: (response) => {
                console.log(response);
            }
        })
    }

    /**
     * 게시글 글쓰기 페이지인지 게시글 수정 페이지인지 확인하고, 확인된 정보에 맞게 페이지에 요소를 추가하는 함수이다.
     */
    const writePageHandler = () => {
        const params = new URLSearchParams(location.search);
        const pno = params.get('pno');

        if (pno !== null) {
            modifyPageHandler(pno);
        } else {
            boardSelectHandler();
        }
    }

    writePageHandler();
})
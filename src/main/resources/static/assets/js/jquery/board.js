$(document).ready(function () {
    /**
     * 게시판 목록을 화면에 출력하는 함수이다.
     */
    const showBoardList = (boardList) => {
        const boardNav = document.getElementById("boardNav");

        let boardMenu = "";

        boardList.forEach((board) => {
            if (board.boardId === board.parentId) {
                boardMenu = document.createElement("div");
                boardMenu.classList.add("board-menu");

                const h4 = document.createElement("h4");
                h4.innerHTML = board.boardName;
                h4.classList.add("board-menu-title")

                boardMenu.appendChild(h4);
                boardNav.appendChild(boardMenu);
            } else {
                const li = document.createElement("li");
                const button = document.createElement("button");

                li.setAttribute("data-bno", board.boardId);
                li.classList.add("board-list");

                button.innerHTML = board.boardName;
                button.classList.add("board-btn");

                li.appendChild(button);
                boardMenu.appendChild(li);
            }
        })
    }

    /**
     * 서버에서 게시판 목록을 가져와 화면에 출력하는 함수이다.
     */
    const handleBoardNav = () => {
        $.ajax({
            type: 'GET',
            url: '/boards',
            headers: { "content-type": "application/json"},
            dataType: 'text',
            success: (response) => {
                const boardList = JSON.parse(response);

                showBoardList(boardList);
            }
        })
    }

    handleBoardNav();
})
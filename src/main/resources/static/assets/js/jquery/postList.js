$(document).ready(function () {
    /**
     * 게시판 목록의 게시판 버튼 클릭 시, 해당 게시판의 게시글 목록을 출력하도록 클릭 이벤트를 추가했다.
     */
    $("#boardNav").on("click", ".board-btn", (event) => {
        const boardId = $(event.currentTarget.parentNode).attr("data-bno");
        showPostListAndPager(1, boardId);
    })

    /**
     * 게시글에서 목록 버튼 클릭 시, 해당 글이 속한 게시판의 게시글 목록을 출력하도록 클릭 이벤트를 추가했다.
     */
    $(".post-controls").on("click", ".btn__list-post", () => {
        const boardId = $(".post-header__board").attr("data-bno");
        showPostListAndPager(1, boardId);
    })

    /**
     * 게시글 목록을 화면에 출력하는 함수이다.
     *
     * @param postList 게시글 목록
     */
    const showPostList = (postList) => {
        const postListBody = document.getElementById("postListBody");

        // 기존에 존재하는 게시글 목록을 삭제
        postListBody.innerHTML = ''

        // 게시글 목록을 테이블에 추가
        postList.forEach((post) => {
            const row = postListBody.insertRow();
            const idCell = row.insertCell();
            const titleCell = row.insertCell();
            const writerCell = row.insertCell();
            const dateCell = row.insertCell();
            const viewsCell = row.insertCell();

            row.setAttribute("data-pno", post.postId);
            idCell.textContent = post.postId;
            $(titleCell).html('<a class="link__post" href='+"/posts/"+post.postId+'>'+post.title+'</a>');
            writerCell.textContent = post.userName;
            dateCell.textContent = new Date(post.createdDate).toLocaleDateString();
            viewsCell.textContent = post.viewCnt;
        })
    }

    /**
     * 페이지 네비게이터를 화면에 출력하는 함수이다.
     *
     * @param currentPage 현재 페이지
     * @param beginPage 현재 페이지를 기준으로 한 시작 번호
     * @param endPage 현재 페이지를 기준으로 한 끝 번호
     * @param totalPageCount 총 페이지 개수
     * @param boardId 화면에 표시될 게시판 ID
     */
    const showPager = ({currentPage, beginPage, endPage, totalPageCount}, boardId) => {
        const pageNav = document.querySelector("#postListPage .page-controls");
        pageNav.innerHTML = "";

        // 이전 페이지 버튼
        if (beginPage > 1) {
            const prevButton = document.createElement("button");
            prevButton.innerText = "&lt;";
            prevButton.addEventListener("click", () => {
                showPostListAndPager(beginPage - 1, boardId);
            });
            pageNav.appendChild(prevButton);
        }

        // 페이지 버튼
        for (let i = beginPage; i <= endPage; i++) {
            const pageButton = document.createElement("button");
            pageButton.innerText = i;
            if (i === currentPage) {
                pageButton.classList.add("current");
            }
            pageButton.addEventListener("click", () => {
                showPostListAndPager(i, boardId);
            });
            pageNav.appendChild(pageButton);
        }

        // 다음 페이지 버튼
        if (endPage < totalPageCount) {
            const nextButton = document.createElement("button");
            nextButton.innerText = "&gt;";
            nextButton.addEventListener("click", () => {
                showPostListAndPager(endPage + 1, boardId);
            });
            pageNav.appendChild(nextButton);
        }
    };

    /**
     * 현재 페이지의 게시글 목록과 페이지 네비게이터를 화면에 출력하는 함수이다.
     */
    const showPostListAndPager = (page, boardId) => {
        let url = '/posts?page='+page;
        if (boardId !== undefined) {
            url += '&boardId='+boardId;
            const boardName = $(`#boardNav [data-bno="${boardId}"] .board-btn`).text();
            const boardTitle = $("#boardTitle");

            boardTitle.html(boardName);
            boardTitle.attr("data-bno", boardId);
        }

        $.ajax({
            type: 'GET',
            url: url,
            headers: { "content-type": "application/json"},
            dataType: 'text',
            success: (response) => {
                const responseJSON = JSON.parse(response);
                const {postList} = responseJSON

                // mainContent 요소 모든 하위 요소들에게 hidden 속성을 부여
                $("#mainContent > *").attr("hidden", "");

                // 게시글 목록 출력
                showPostList(postList);

                // 페이지 네비게이터 출력
                showPager(responseJSON, boardId);

                $("#postListContainer").removeAttr("hidden");
            }
        })
    }

    showPostListAndPager(1);
})
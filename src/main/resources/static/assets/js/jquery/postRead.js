$(document).ready(function () {
    const postControls = $(".post-controls");

    /**
     * 게시판 목록의 게시글 제목 클릭 시, 해당 게시글을 출력하도록 클릭 이벤트를 추가했다.
     */
    $("#postListBody").on("click", ".link__post", (event) => {
        event.preventDefault();
        const pno = $(event.currentTarget).closest("tr").attr("data-pno");
        showPost(pno);
    })

    /**
     * 게시글에서 글쓰기 버튼 클릭 시, 글쓰기 페이지로 이동하도록 클릭 이벤트를 추가했다.
     */
    postControls.on("click", ".btn__create-post", () => {
        window.location.href = "/posts/new";
    })

    /**
     * 게시글에서 수정 버튼 클릭 시, 수정 페이지로 이동하도록 클릭 이벤트를 추가했다.
     */
    postControls.on("click", ".btn__modify-post", () => {
        const pno = $(".post-header__title").attr("data-pno");
        window.location.href = "/posts/new?pno="+pno;
    })

    /**
     * 게시글에서 삭제 버튼 클릭 시, 해당 글이 삭제되도록 클릭 이벤트를 추가했다.
     */
    postControls.on("click", ".btn__remove-post", () => {
        const pno = $(".post-header__title").attr("data-pno");

        $.ajax({
            type: 'DELETE',
            url: "/posts/" + pno,
            headers: { "content-type": "application/json"},
            dataType: 'text',
            success: () => {
                alert("성공적으로 삭제되었습니다.");
                window.location.href = "/";
            },
            error: () => {
                alert("삭제에 실패하였습니다. 다시 시도해주세요.");
            }
        })
    })

    /**
     * 입력받은 게시글을 화면에 출력하는 함수이다.
     *
     * @param post 화면에 표시할 게시글 정보
     */
    const printPost = (post) => {
        const board = $("#postPage .post-header__board");
        board.html(post.boardName);
        board.attr("data-bno", post.boardId);

        const postTitle = $("#postPage .post-header__title");
        postTitle.html(post.title);
        postTitle.attr("data-pno", post.postId);

        $("#postPage .post-header__writer").html(post.userName);
        $("#postPage .post-header__created-date").html(new Date(post.createdDate).toLocaleDateString());
        $("#postPage .post-header__view-cnt").html(post.viewCnt);

        $("#postPage .post-content").html(post.content);
    }

    /**
     * 게시글을 서버에서 가져와 화면에 출력하는 함수이다.
     *
     * @param pno 화면에 출력할 게시글 ID
     */
    const showPost = (pno) => {
        $.ajax({
            type: 'GET',
            url: "/posts/" + pno,
            headers: { "content-type": "application/json"},
            dataType: 'text',
            success: (response) => {
                const post = JSON.parse(response);

                // mainContent 요소 모든 하위 요소들에게 hidden 속성을 부여
                $("#mainContent > *").attr("hidden", "");

                printPost(post);

                $("#postContainer").removeAttr("hidden");
            },
            error: (response) => {
                console.log(response);
            }
        })
    }
})
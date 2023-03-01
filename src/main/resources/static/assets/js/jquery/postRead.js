$(document).ready(function () {
    const getUserId = () => {
        return new Promise((resolve, reject) => {
            $.ajax({
                type: 'GET',
                url: '/users/me',
                headers: { 'content-type': 'application/json' },
                dataType: 'text',
                success: (response) => {
                    resolve(response);
                },
                error: (error) => {
                    reject(new Error('로그인이 필요한 서비스입니다.'));
                },
            });
        });
    };

    /**
     * 게시판 목록의 게시글 제목 클릭 시, 해당 게시글을 출력하도록 클릭 이벤트를 추가했다.
     */
    $("#postListBody").on("click", ".link__post", (event) => {
        event.preventDefault();
        const pno = $(event.currentTarget).closest("tr").attr("data-pno");
        showPost(pno);
    })

    /**
     * 게시글에서 삭제 버튼 클릭 시, 해당 글이 삭제되도록 클릭 이벤트를 추가했다.
     */
    $(".post-controls").on("click", ".btn__remove-post", (event) => {
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
            error: (response) => {
                alert("삭제에 실패하였습니다. 다시 시도해주세요.");
            }
        })
    })

    /**
     * 입력받은 게시글을 화면에 출력하는 함수이다.
     *
     * @param post 화면에 표시할 게시글 정보
     * @param userId 현재 로그인 중인 사용자 ID
     */
    const printPost = (post, userId) => {
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
     * @param userId 현재 로그인 중인 사용자 ID
     */
    const getPost = (pno, userId) => {
        $.ajax({
            type: 'GET',
            url: "/posts/" + pno,
            headers: { "content-type": "application/json"},
            dataType: 'text',
            success: (response) => {
                const post = JSON.parse(response);

                // mainContent 요소 모든 하위 요소들에게 hidden 속성을 부여
                $("#mainContent > *").attr("hidden", "");

                printPost(post, userId);

                $("#postContainer").removeAttr("hidden");
            },
            error: (response) => {
                console.log(response);
            }
        })
    }

    const showPost = (pno) => {
        getUserId()
            .then((userId) => {
                getPost(pno, userId);
            })
            .catch((error) => {
                alert(error.message);
                window.location.href = '/login';
            })
    }
})
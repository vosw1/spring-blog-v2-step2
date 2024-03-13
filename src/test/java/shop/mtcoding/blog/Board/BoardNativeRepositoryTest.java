package shop.mtcoding.blog.Board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardNativeRepository;
import shop.mtcoding.blog.board.BoardPersistRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(BoardNativeRepository.class)
@DataJpaTest
public class BoardNativeRepositoryTest {

    @Autowired // DI
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void updateById_test(){
        // given
        int id = 1;
        String title = "제목수정1";
        String content = "내용수정1";
        String username = "이름수정1";

        // when
        boardNativeRepository.updateById(id, title, content, username);

        // then
        Board board = boardNativeRepository.findById(id);
        System.out.println("updateById_test/board : "+board);
        assertThat(board.getTitle()).isEqualTo("제목수정2");
        assertThat(board.getContent()).isEqualTo("내용수정1");
        assertThat(board.getUsername()).isEqualTo("이름수정1");
    }

    @Test
    public void findById_test() {
        //given - 지금은 넣을게 없음
        int id = 1;
        //when
        Board board = boardNativeRepository.findById(id);
        System.out.println("findById_test : " + board);
        //then
        //org.assertj.core.api
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");

    }

    @Test
    public void findAll_test() {
        //given - 지금은 넣을게 없음

        //when
        List<Board> boardList = boardNativeRepository.findAll();

        //then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        //org.assertj.core.api
        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardNativeRepository.deleteById(id);

        // then
        List<Board> boardList = boardNativeRepository.findAll();
        assertThat(boardList.size()).isEqualTo(2);
    }
}
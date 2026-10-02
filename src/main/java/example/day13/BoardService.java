
 	
package example.day13;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor 
public class BoardService {

    private final BoardRepository boardRepository;
    // [1] 등록
    public boolean boardWrite(BoardDto dto) {
            BoardEntity entity = dto.toEntity();
            boardRepository.save(entity);
            return true;
    }
    // [2] 전체 조회
    public List<BoardDto> boardFindAll() {
        return boardRepository.findAll().stream()
                .map(BoardDto::fromEntity)
                .collect(Collectors.toList());
    }
    // [3] 개별 조회
    public BoardDto boardFindById(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElse(null);
        if (entity != null) {
            return BoardDto.fromEntity(entity);
        }
        return null;
    }
}
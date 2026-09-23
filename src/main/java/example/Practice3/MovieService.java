package example.Practice3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


 	
@Service
public class MovieService {
    @Autowired MovieRepository movieRepository;

    // 1.
    public boolean 영화등록( MovieDto movieDto ){
        // 1. DTO -> ENTITY
        MovieEntity entity = movieDto.toEntity();
        // 2. entity save
        MovieEntity savedEntity = movieRepository.save( entity );
        // 3.
        if( savedEntity.getMovieid() >= 1 ){return true; } // 3.
        else{ return false; }
    }
    
    // 2.
    public List<MovieDto> 영화전체조회(){
        // 1. findAll 엔티티 전체조회
        List<MovieEntity> entities = movieRepository.findAll();
        // 2. 엔티티 -> dto 변환
        List<MovieDto> list = new ArrayList<>();
        entities.forEach( (entity) -> {
            MovieDto dto = MovieDto.from( entity );
            list.add(dto);

        });
        return list;
    }

    // 3.
    public MovieDto 영화개별조회( int movieid ){
        Optional<MovieEntity> optional = movieRepository.findById( movieid );// 1. findById 엔티티 개별조회
        if( optional.isPresent() ) { // 2. 조회 결과 존재하면 
            MovieEntity entity = optional.get(); // 3. 엔티티 꺼내기 
            return MovieDto.from(entity);
        }
        return null; // 참조(객체) 에서는 null 없다는 뜻 
    }

    // 4.
    @Transactional
    public boolean 영화수정( MovieDto movieDto ){
        // 1. 수정할 pk 이용하여 엔티티 찾기
        Optional<MovieEntity> optional = movieRepository.findById( movieDto.getMovieid() );
        if( optional.isPresent() ){ // 2. 존재하면 엔티티 수정한다.
            MovieEntity entity = optional.get();
            entity.setDirector( movieDto.getDirector() );
            entity.setRating( movieDto.getRating() );
            entity.setReleasedate( movieDto.getReleasedate() );
            entity.setTitle( movieDto.getTitle() );
            return true;
        } 
        return false; 
    }

    // 5.
    public boolean 영화삭제( int movieid ){
        // 1. 삭제할 pk 이용하여 엔티티 존재확인
        Optional<MovieEntity> optional = movieRepository.findById(movieid);
        if( optional.isPresent() ){ // 2. 존재하면 삭제 .deleteById
            movieRepository.deleteById( movieid );
            return true;
        }
        return false;
    }

} // class end

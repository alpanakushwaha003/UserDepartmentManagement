package com.javalearn.learnSpringBoot.search;
import com.javalearn.learnSpringBoot.dto.UserDTO;
import org.springframework.stereotype.Service;
import com.javalearn.learnSpringBoot.repository.UserSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserSearchService {
    @Autowired
    private UserSearchRepository repository;
//    public List<UserDTO> search(String query){
//        return repository.findByNameContainingIgnoreCase(query).stream().map(doc ->{
//            UserDTO dto=new UserDTO();
//            dto.setId(doc.getId());
//            dto.setName(doc.getName());
//            dto.setEmail(doc.getEmail());
//            dto.setDepartmentName(doc.getDepartmentName());
//            return dto;
//        }).toList();
//    }

    //updated search to include department name
public List<UserDTO> search(String query) {
    return repository
            .findByNameContainingIgnoreCaseOrDepartmentNameContainingIgnoreCase(
                    query, query
            )
            .stream()
            .map(doc -> {
                UserDTO dto = new UserDTO();
                dto.setId(doc.getId());
                dto.setName(doc.getName());
                dto.setEmail(doc.getEmail());
                dto.setDepartmentName(doc.getDepartmentName());
                return dto;
            })
            .toList();
}

}


package uef.edu.vn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uef.edu.vn.model.Topic;
import uef.edu.vn.repository.TopicRepository;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public List<Topic> getAvailableTopics() {
        return topicRepository.findAvailableTopics();
    }

    public Topic getTopicById(int id) {
        return topicRepository.findById(id);
    }

    public List<Topic> searchTopics(String keyword) {
        return topicRepository.search(keyword);
    }

    public void saveTopic(Topic topic) {
        if (topic.getTopicID() == 0) {
            topicRepository.save(topic);
        } else {
            topicRepository.update(topic);
        }
    }
}
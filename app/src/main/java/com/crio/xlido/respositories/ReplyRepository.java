package com.crio.xlido.respositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.xlido.entities.Reply;

public class ReplyRepository implements IReplyRepository{
    private final Map<Long, List<Reply>> storage = new HashMap<>();
    @Override
    public Reply add(Long questionId, Reply reply) {
        List<Reply> replyList = storage.getOrDefault(questionId, new ArrayList<>());
        replyList.add(reply);
        storage.putIfAbsent(questionId, replyList);
        return reply;
    }
    public List<Reply> getReply(Long questionId){
        return new ArrayList<>(storage.getOrDefault(questionId, new ArrayList<>()));
    }
    
}

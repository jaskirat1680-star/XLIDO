package com.crio.xlido.respositories;

import java.util.List;
import com.crio.xlido.entities.Reply;

public interface IReplyRepository {
    Reply add(Long questionId, Reply reply);
    List<Reply> getReply(Long questionId);
}

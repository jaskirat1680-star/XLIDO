package com.crio.xlido.utils;

import java.util.Comparator;
import com.crio.xlido.entities.Question;

public class PopularQuestionComparator implements Comparator<Question>{

    @Override
    public int compare(Question arg0, Question arg1) {
        return arg1.getVotes().size() - arg0.getVotes().size();
    }

}

package org.repository;
import java.util.*;
import org.model.AminityModel;

public interface AminityRepository {
    public boolean isAddNewAminity(AminityModel model);
    public List<AminityModel> getAllAminity();
}

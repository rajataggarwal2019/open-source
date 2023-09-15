package com.razik.market.service;

import com.razik.market.Constant;
import com.razik.market.data.Repository;
import com.razik.market.data.TreeEntity;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    @Autowired
    private EntityManager entityManager;

    public List<TreeEntity> getChildren(Long parentId) {
        return repository.getChildren(parentId);
    }

    public TreeEntity findParentById(Long id) {
        return repository.getParent(id, Constant.ROOT_ID);
    }

    public TreeEntity save(TreeDTO treeDTO) {
        TreeEntity treeEntity = treeMapper(treeDTO);
        if (StringUtils.isEmpty(treeDTO.getUrl())) {
            treeEntity.setUrl(Constant.INDEX_PATH + Constant.ROOT_ID);
        }
        repository.save(treeEntity);
        if (StringUtils.isEmpty(treeDTO.getUrl())) {
            treeEntity.setUrl(Constant.INDEX_PATH + treeEntity.getId());
        }
        repository.save(treeEntity);
        return treeEntity;
    }

    private TreeEntity treeMapper(TreeDTO treeDTO) {
        return TreeEntity.builder().url(treeDTO.getUrl()).parent(treeDTO.getParent()).name(treeDTO.getName()).build();
    }

}

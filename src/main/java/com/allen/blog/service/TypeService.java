package com.allen.blog.service;

import com.allen.blog.bean.Type;
import com.allen.blog.dao.TypeRepository;
import com.allen.blog.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Transactional
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional
    public Type updateType(Long id, Type type) {
        Type t = getType(id);
        if (t == null) {
            throw new NotFoundException("该类型不存在");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }

    @Transactional
    public void deteleType(Long id) {
        typeRepository.deleteById(id);
    }


}

package com.allen.blog.service;

import com.allen.blog.bean.Tag;
import com.allen.blog.dao.TagRepository;
import com.allen.blog.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Transactional
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Transactional
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    @Transactional
    public List<Tag> listTag(String ids) {
        List<Long> _ids = convertToList(ids);
        return tagRepository.findAllById(_ids);
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    public Tag updateTag(Long id, Tag tag) {
        Tag t = getTag(id);
        if (t == null) {
            throw new NotFoundException("该类型不存在");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    @Transactional
    public void deteleTag(Long id) {
        tagRepository.deleteById(id);
    }

}

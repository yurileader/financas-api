package com.yurileader.financasapi.repository;

import com.yurileader.financasapi.model.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
    @Override
    Page<Lancamento> findAll(Pageable pageable);
}

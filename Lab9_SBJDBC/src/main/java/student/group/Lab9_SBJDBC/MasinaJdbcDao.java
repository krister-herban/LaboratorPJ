package student.group.Lab9_SBJDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MasinaJdbcDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Masina> findAll(){
        String sql = "select * from masini";
        return jdbcTemplate.query(sql, new MasinaMapper());
    }

    public Masina findById(String nrInm){
        String sql = "select * from masini where numar_inmatriculare = ?";
        return jdbcTemplate.queryForObject(sql, new MasinaMapper(), nrInm);
    }

    public int deleteById(String nrInm){
        String sql = "delete from masini where numar_inmatriculare = ?";
        return jdbcTemplate.update(sql, nrInm);
    }

    public int insert(Masina masina){
        String sql="insert into masini values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, masina.getNumarInmatriculare(),
                masina.getMarca(), masina.getAnulFabricatiei(),
                masina.getCuloarea(), masina.getNrKm());
    }

    public int update(Masina masina){
        String sql="update masini set numar_inmatriculare=?, marca=?, anul_fabricatiei=?, " +
                "culoarea=?, nr_km=? where numar_inmatriculare = ?";
        return jdbcTemplate.update(sql, masina.getNumarInmatriculare(),
                masina.getMarca(), masina.getAnulFabricatiei(),
                masina.getCuloarea(), masina.getNrKm());
    }
}


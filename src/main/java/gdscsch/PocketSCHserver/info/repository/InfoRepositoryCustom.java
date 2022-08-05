package gdscsch.PocketSCHserver.info.repository;

import gdscsch.PocketSCHserver.info.entity.Info;
import gdscsch.PocketSCHserver.info.entity.Keyword;
import java.util.List;

public interface InfoRepositoryCustom {
    List<Info> findInfoByTitles(List<Keyword> keywords);
}

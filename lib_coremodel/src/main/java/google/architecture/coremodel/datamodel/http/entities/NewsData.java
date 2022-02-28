package google.architecture.coremodel.datamodel.http.entities;

import java.util.List;

/**
 * Created by clark on 2022/02/28.
 */

public class NewsData {

    private String date;
    private List<ResultsBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ResultsBean> getStories() {
        return stories;
    }

    public void setStories(List<ResultsBean> stories) {
        this.stories = stories;
    }

    public static class ResultsBean {


        private String id;
        private String image_hue;
        private String title;
        private String hint;
        private String gaPrefix;
        private Integer typeX;
        private Integer idX;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public Integer getTypeX() {
            return typeX;
        }

        public void setTypeX(Integer typeX) {
            this.typeX = typeX;
        }

        public Integer getIdX() {
            return idX;
        }

        public void setIdX(Integer idX) {
            this.idX = idX;
        }
    }
}

(defproject tokiilo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "GPL-3.0"
            :url "https://www.gnu.org/licenses/gpl-3.0.txt"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.blancas/kern "1.1.0"]]
  :main ^:skip-aot tokiilo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

(defproject minikube-test-node "0.0.7-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 ]

  :release-tasks [["clean"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["container" "build"]
                  ["container" "push"]]

  :container {:name "minikube-test-node"
              :dockerfile "/docker"
              :hub "sforzando-dockerv2-local.jfrog.io"}

  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]
                                  [cheshire "5.5.0"]
                                  [ring/ring-mock "0.3.0"]]
                   :plugins [[lein-ring "0.12.0"]
                             [clj-plugin "0.6.2"]
                             [lein-metajar "0.1.1"]]}}

  :repositories ^:replace [["clojars" {:url "https://clojars.org/repo"}]
                           ["central" {:url "https://repo1.maven.org/maven2/" :snapshots false}]
                           ["releases" {:url "https://sforzando.jfrog.io/sforzando/libs-release-local"
                                        :username [:gpg :env/artifactory_user]
                                        :password [:gpg :env/artifactory_pwd]}]
                           ["plugins" {:url "https://sforzando.jfrog.io/sforzando/plugins-release"
                                       :username [:gpg :env/artifactory_user]
                                       :password [:gpg :env/artifactory_pwd]}]])

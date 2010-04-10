# Faker

Faker is a port of [Ruby Faker library](http://faker.rubyforge.org/) to the clojure programming language.

Right now only name generation is implemented.

## Installation

The easiest way is to use Leiningen. Add the following dependency to your project.clj file:

    [faker "0.1.0-SNAPSHOT"]


## Usage

    (ns test
      (:use faker.name))

    (def fake-name (gen-name))

# Faker

Faker is a port of [Ruby Faker library](http://faker.rubyforge.org/) to the
clojure programming language.

It is useful when you want to generate fake but good-looking data, such as in
test scenarios or staging servers.

## Installation

The easiest way is to use Leiningen. Add the following dependency to your
project.clj file:

    [faker "0.2.2"]


## Usage

    (ns test
      (:use faker.name faker.lorem))

    (def n (take 10 (names)))
    (def p (take 10 (paragraphs)))

There are several more namespaces for generating:

* Person names
* Company names
* Addresses
* Domains and emails
* Telephone numbers
* Text

Take a look at the documentation generated with autodoc
[here](http://paraseba.github.com/faker)

## License

Source Copyright © 2010-2015 Sebastian Galkin. Distributed under the
Eclipse Public License, the same as Clojure uses. See the file LICENSE.


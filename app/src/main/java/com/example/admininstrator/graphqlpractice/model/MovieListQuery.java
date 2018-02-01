package com.example.admininstrator.graphqlpractice.model;

import com.apollographql.apollo.api.InputFieldMarshaller;
import com.apollographql.apollo.api.InputFieldWriter;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("Apollo GraphQL")
public final class MovieListQuery implements Query<MovieListQuery.Data, MovieListQuery.Data, MovieListQuery.Variables> {
  public static final String OPERATION_DEFINITION = "query MovieListQuery($title: String!) {\n"
      + "  movies(subString: $title, limit: 100) {\n"
      + "    __typename\n"
      + "    title\n"
      + "    year\n"
      + "    imdbRating\n"
      + "    poster\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  private static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "MovieListQuery";
    }
  };

  private final MovieListQuery.Variables variables;

  public MovieListQuery(@Nonnull String title) {
    Utils.checkNotNull(title, "title == null");
    variables = new MovieListQuery.Variables(title);
  }

  @Override
  public String operationId() {
    return "c25f4c60ee25a50b9982a3c557ced93afe234a447548a5a5e621a36ce00c0955";
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public MovieListQuery.Data wrapData(MovieListQuery.Data data) {
    return data;
  }

  @Override
  public MovieListQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public OperationName name() {
    return OPERATION_NAME;
  }

  public static final class Builder {
    private @Nonnull String title;

    Builder() {
    }

    public Builder title(@Nonnull String title) {
      this.title = title;
      return this;
    }

    public MovieListQuery build() {
      Utils.checkNotNull(title, "title == null");
      return new MovieListQuery(title);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final @Nonnull String title;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(@Nonnull String title) {
      this.title = title;
      this.valueMap.put("title", title);
    }

    public @Nonnull String title() {
      return title;
    }

    @Override
    public Map<String, Object> valueMap() {
      return Collections.unmodifiableMap(valueMap);
    }

    @Override
    public InputFieldMarshaller marshaller() {
      return new InputFieldMarshaller() {
        @Override
        public void marshal(InputFieldWriter writer) throws IOException {
          writer.writeString("title", title);
        }
      };
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forList("movies", "movies", new UnmodifiableMapBuilder<String, Object>(2)
        .put("subString", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "title")
        .build())
        .put("limit", "30.0")
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nullable List<Movie> movies;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Data(@Nullable List<Movie> movies) {
      this.movies = movies;
    }

    public @Nullable List<Movie> movies() {
      return this.movies;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeList($responseFields[0], movies, new ResponseWriter.ListWriter() {
            @Override
            public void write(Object value, ResponseWriter.ListItemWriter listItemWriter) {
              listItemWriter.writeObject(((Movie) value).marshaller());
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "movies=" + movies
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return ((this.movies == null) ? (that.movies == null) : this.movies.equals(that.movies));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (movies == null) ? 0 : movies.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final Movie.Mapper movieFieldMapper = new Movie.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final List<Movie> movies = reader.readList($responseFields[0], new ResponseReader.ListReader<Movie>() {
          @Override
          public Movie read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Movie>() {
              @Override
              public Movie read(ResponseReader reader) {
                return movieFieldMapper.map(reader);
              }
            });
          }
        });
        return new Data(movies);
      }
    }
  }

  public static class Movie {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("title", "title", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forLong("year", "year", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forDouble("imdbRating", "imdbRating", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("poster", "poster", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nullable String title;

    final @Nullable Long year;

    final @Nullable Double imdbRating;

    final @Nullable String poster;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Movie(@Nonnull String __typename, @Nullable String title, @Nullable Long year,
        @Nullable Double imdbRating, @Nullable String poster) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.title = title;
      this.year = year;
      this.imdbRating = imdbRating;
      this.poster = poster;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nullable String title() {
      return this.title;
    }

    public @Nullable Long year() {
      return this.year;
    }

    public @Nullable Double imdbRating() {
      return this.imdbRating;
    }

    public @Nullable String poster() {
      return this.poster;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], title);
          writer.writeLong($responseFields[2], year);
          writer.writeDouble($responseFields[3], imdbRating);
          writer.writeString($responseFields[4], poster);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Movie{"
          + "__typename=" + __typename + ", "
          + "title=" + title + ", "
          + "year=" + year + ", "
          + "imdbRating=" + imdbRating + ", "
          + "poster=" + poster
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Movie) {
        Movie that = (Movie) o;
        return this.__typename.equals(that.__typename)
         && ((this.title == null) ? (that.title == null) : this.title.equals(that.title))
         && ((this.year == null) ? (that.year == null) : this.year.equals(that.year))
         && ((this.imdbRating == null) ? (that.imdbRating == null) : this.imdbRating.equals(that.imdbRating))
         && ((this.poster == null) ? (that.poster == null) : this.poster.equals(that.poster));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= (title == null) ? 0 : title.hashCode();
        h *= 1000003;
        h ^= (year == null) ? 0 : year.hashCode();
        h *= 1000003;
        h ^= (imdbRating == null) ? 0 : imdbRating.hashCode();
        h *= 1000003;
        h ^= (poster == null) ? 0 : poster.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Movie> {
      @Override
      public Movie map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String title = reader.readString($responseFields[1]);
        final Long year = reader.readLong($responseFields[2]);
        final Double imdbRating = reader.readDouble($responseFields[3]);
        final String poster = reader.readString($responseFields[4]);
        return new Movie(__typename, title, year, imdbRating, poster);
      }
    }
  }
}

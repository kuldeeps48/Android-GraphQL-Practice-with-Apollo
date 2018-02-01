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
public final class SimilarMoviesQuery implements Query<SimilarMoviesQuery.Data, SimilarMoviesQuery.Data, SimilarMoviesQuery.Variables> {
  public static final String OPERATION_DEFINITION = "query SimilarMoviesQuery($title: String!) {\n"
      + "  movies(subString: $title, limit: 1) {\n"
      + "    __typename\n"
      + "    similar {\n"
      + "      __typename\n"
      + "      poster\n"
      + "      title\n"
      + "    }\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  private static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "SimilarMoviesQuery";
    }
  };

  private final SimilarMoviesQuery.Variables variables;

  public SimilarMoviesQuery(@Nonnull String title) {
    Utils.checkNotNull(title, "title == null");
    variables = new SimilarMoviesQuery.Variables(title);
  }

  @Override
  public String operationId() {
    return "6e612c5ab6ac03e6339740178a3ef3742cad5971f09ce85285f883a97d7797a4";
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public SimilarMoviesQuery.Data wrapData(SimilarMoviesQuery.Data data) {
    return data;
  }

  @Override
  public SimilarMoviesQuery.Variables variables() {
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

    public SimilarMoviesQuery build() {
      Utils.checkNotNull(title, "title == null");
      return new SimilarMoviesQuery(title);
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
        .put("limit", "1.0")
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
      ResponseField.forList("similar", "similar", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nullable List<Similar> similar;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Movie(@Nonnull String __typename, @Nullable List<Similar> similar) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.similar = similar;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nullable List<Similar> similar() {
      return this.similar;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeList($responseFields[1], similar, new ResponseWriter.ListWriter() {
            @Override
            public void write(Object value, ResponseWriter.ListItemWriter listItemWriter) {
              listItemWriter.writeObject(((Similar) value).marshaller());
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Movie{"
          + "__typename=" + __typename + ", "
          + "similar=" + similar
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
         && ((this.similar == null) ? (that.similar == null) : this.similar.equals(that.similar));
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
        h ^= (similar == null) ? 0 : similar.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Movie> {
      final Similar.Mapper similarFieldMapper = new Similar.Mapper();

      @Override
      public Movie map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final List<Similar> similar = reader.readList($responseFields[1], new ResponseReader.ListReader<Similar>() {
          @Override
          public Similar read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Similar>() {
              @Override
              public Similar read(ResponseReader reader) {
                return similarFieldMapper.map(reader);
              }
            });
          }
        });
        return new Movie(__typename, similar);
      }
    }
  }

  public static class Similar {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("poster", "poster", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("title", "title", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nullable String poster;

    final @Nullable String title;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Similar(@Nonnull String __typename, @Nullable String poster, @Nullable String title) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.poster = poster;
      this.title = title;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nullable String poster() {
      return this.poster;
    }

    public @Nullable String title() {
      return this.title;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], poster);
          writer.writeString($responseFields[2], title);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Similar{"
          + "__typename=" + __typename + ", "
          + "poster=" + poster + ", "
          + "title=" + title
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Similar) {
        Similar that = (Similar) o;
        return this.__typename.equals(that.__typename)
         && ((this.poster == null) ? (that.poster == null) : this.poster.equals(that.poster))
         && ((this.title == null) ? (that.title == null) : this.title.equals(that.title));
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
        h ^= (poster == null) ? 0 : poster.hashCode();
        h *= 1000003;
        h ^= (title == null) ? 0 : title.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Similar> {
      @Override
      public Similar map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String poster = reader.readString($responseFields[1]);
        final String title = reader.readString($responseFields[2]);
        return new Similar(__typename, poster, title);
      }
    }
  }
}

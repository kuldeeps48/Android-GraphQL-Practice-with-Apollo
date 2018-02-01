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
public final class ExtraMovieDetailsQuery implements Query<ExtraMovieDetailsQuery.Data, ExtraMovieDetailsQuery.Data, ExtraMovieDetailsQuery.Variables> {
  public static final String OPERATION_DEFINITION = "query ExtraMovieDetailsQuery($title: String!) {\n"
      + "  movies(subString: $title, limit: 1) {\n"
      + "    __typename\n"
      + "    plot\n"
      + "    genres\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  private static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "ExtraMovieDetailsQuery";
    }
  };

  private final ExtraMovieDetailsQuery.Variables variables;

  public ExtraMovieDetailsQuery(@Nonnull String title) {
    Utils.checkNotNull(title, "title == null");
    variables = new ExtraMovieDetailsQuery.Variables(title);
  }

  @Override
  public String operationId() {
    return "8e4493932fd879108f5560c34b193019716bdedc27ce8fadcb92d7032022a48f";
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public ExtraMovieDetailsQuery.Data wrapData(ExtraMovieDetailsQuery.Data data) {
    return data;
  }

  @Override
  public ExtraMovieDetailsQuery.Variables variables() {
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

    public ExtraMovieDetailsQuery build() {
      Utils.checkNotNull(title, "title == null");
      return new ExtraMovieDetailsQuery(title);
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
      ResponseField.forString("plot", "plot", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forList("genres", "genres", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nullable String plot;

    final @Nullable List<String> genres;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Movie(@Nonnull String __typename, @Nullable String plot, @Nullable List<String> genres) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.plot = plot;
      this.genres = genres;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nullable String plot() {
      return this.plot;
    }

    public @Nullable List<String> genres() {
      return this.genres;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], plot);
          writer.writeList($responseFields[2], genres, new ResponseWriter.ListWriter() {
            @Override
            public void write(Object value, ResponseWriter.ListItemWriter listItemWriter) {
              listItemWriter.writeString(value);
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
          + "plot=" + plot + ", "
          + "genres=" + genres
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
         && ((this.plot == null) ? (that.plot == null) : this.plot.equals(that.plot))
         && ((this.genres == null) ? (that.genres == null) : this.genres.equals(that.genres));
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
        h ^= (plot == null) ? 0 : plot.hashCode();
        h *= 1000003;
        h ^= (genres == null) ? 0 : genres.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Movie> {
      @Override
      public Movie map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String plot = reader.readString($responseFields[1]);
        final List<String> genres = reader.readList($responseFields[2], new ResponseReader.ListReader<String>() {
          @Override
          public String read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readString();
          }
        });
        return new Movie(__typename, plot, genres);
      }
    }
  }
}
